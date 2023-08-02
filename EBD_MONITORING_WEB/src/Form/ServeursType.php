<?php

namespace App\Form;

use App\Entity\Serveurs;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ServeursType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('ref')
            ->add('etat')
            ->add('platforme')
            ->add('hostname')
            ->add('fqdn')
            ->add('type')
            ->add('modele')
            ->add('os')
            ->add('verTechFirmware')
            ->add('cluster')
            ->add('ipSource')
            ->add('description')
            ->add('socleStandardOmu')
            ->add('complementsInformations')
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Serveurs::class,
        ]);
    }
}
