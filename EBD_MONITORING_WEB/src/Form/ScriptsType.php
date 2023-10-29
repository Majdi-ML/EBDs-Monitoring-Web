<?php

namespace App\Form;

use App\Entity\Scripts;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;

class ScriptsType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
        ->add('id',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('ref',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('etat', ChoiceType::class, [
            'attr' => [
                'class' => 'form-select',
            ],
            'choices'  => [
            'Nouveau' => "Nouveau" ,
            'Supprimé'=>"Supprimé",
            'Modifié' => "Modifié",
            'Inchangé' => "Inchangé" ,
            
        ],
        ])
        ->add('refComposant',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('rgSgSiCluster',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('script',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('codeErreur',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('criticite', ChoiceType::class, [
            'attr' => [
                'class' => 'form-select',
            ],
            'choices'  => [
            'Critique' => "Critique" ,
            'Majeure'=>"Majeure",
            'Normale' => "Normale",
            
        ],
        ])
        ->add('description',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('instructions',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('monitoredBy', ChoiceType::class, [
            'attr' => [
                'class' => 'form-select',
            ],
            'choices'  => [
            'OMU' => "OMU" ,
            'Sitescope 1'=>"Sitescope 1",
            'Sitescope 2' => "Sitescope 2" ,
            'NNMI' => "NNMI",
            'RUM' => "RUM" ,
            'BPM' => "BPM" ,
            
        ],
        ])
        ->add('refService',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('support',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Scripts::class,
            'attr' => [
                // Ajoutez l'attribut "novalidate" au formulaire
                'novalidate' => 'novalidate',
            ],
        ]);
    }
}
