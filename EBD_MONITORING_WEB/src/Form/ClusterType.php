<?php

namespace App\Form;

use App\Entity\Cluster;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\TextType;

class ClusterType extends AbstractType
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
            ->add('etat',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('nomDuRessourceGroupPackageServiceGuard',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('adresseIp',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('listeDesServeursConcernes',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('logicielCluster',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('version',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('mode',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('serveurActif',TextType::class,[
                'attr' => [
                    'class' => 'form-control',
                ],
            ])
            ->add('complementsInformations',TextType::class,[
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
            'data_class' => Cluster::class,
            'attr' => [
                // Ajoutez l'attribut "novalidate" au formulaire
                'novalidate' => 'novalidate',
            ],
        ]);
    }
}
