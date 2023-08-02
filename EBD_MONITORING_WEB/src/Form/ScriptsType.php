<?php

namespace App\Form;

use App\Entity\Scripts;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ScriptsType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('ref')
            ->add('etat')
            ->add('refComposant')
            ->add('rgSgSiCluster')
            ->add('script')
            ->add('codeErreur')
            ->add('criticite')
            ->add('description')
            ->add('instructions')
            ->add('monitoredBy')
            ->add('refService')
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Scripts::class,
        ]);
    }
}
